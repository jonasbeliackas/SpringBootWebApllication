package com.example.web.controllers;

import com.example.web.model.DataFromGraphPage;
import com.example.web.repositorys.CurrencyRepository;
import com.example.web.repositorys.GraphMapInterface;
import com.example.web.services.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.xpath.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
@Controller
public class HighChartController {

    @Qualifier("graphMap")
    @Autowired
    GraphMapInterface graphMapInterface;

    @Autowired
    CurrencyRepository repo;

    private boolean error;

    @RequestMapping(value = "/graphs", method = RequestMethod.GET)
    public String showGraphsEmptyForm(@ModelAttribute("chartData") DataFromGraphPage dataFromGraphPage, Model model)
    {
        model.addAttribute("currency",repo.findAll());

        return "graphs";
    }


    @RequestMapping(value = "/graphs", method = RequestMethod.POST)
    public String showGraphsFilledForm(@ModelAttribute("chartData") DataFromGraphPage dataFromGraphPage, Model model) {

        dataFromGraphPage.setName(repo.findById((long) dataFromGraphPage.getId()).get().getCurrencyName());
        model.addAttribute("currency", repo.findAll());
        Map<String, Double> graphData = new TreeMap<>();
        try {
            String xml = Server.connectAndReadXmlToString("http://www.lb.lt/webservices/FxRates/FxRates.asmx/getFxRatesForCurrency?tp=EU&ccy=" + dataFromGraphPage.getName() + "&dtFrom=" + dataFromGraphPage.getDataFrom() + "&dtTo=" + dataFromGraphPage.getDataTo());
            Document document = Server.getXmlDocument(xml);
            XPath xpath = Server.getXpath();
            //create XPathExpression object
            XPathExpression expressionForDate =
                    xpath.compile("/FxRates/FxRate/Dt/text()");
            XPathExpression expressionForValue =
                    xpath.compile("/FxRates/FxRate/CcyAmt[2]/Amt/text()");
            XPathExpression expressionForError = xpath.compile("/FxRates/OprlErr/Desc/text()");
            model.addAttribute("error",(String)expressionForError.evaluate(document));
            NodeList nodesOfDates = (NodeList) expressionForDate.evaluate(document, XPathConstants.NODESET);
            NodeList nodesOfValues = (NodeList) expressionForValue.evaluate(document, XPathConstants.NODESET);

            for (int i = 0; i < nodesOfDates.getLength(); i++) {
                graphData.put(nodesOfDates.item(i).getNodeValue(), Double.parseDouble(nodesOfValues.item(i).getNodeValue()));
            }
            graphMapInterface.setGraphData(graphData);
        } catch (XPathExpressionException e) {
            e.printStackTrace();


        }
        return "graphs";
    }

}
