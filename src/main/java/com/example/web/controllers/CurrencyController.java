package com.example.web.controllers;

import com.example.web.domain.Currency;
import com.example.web.repositorys.CurrencyRepository;
import com.example.web.services.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.xpath.*;

@Component
@RestController
public class CurrencyController {

    @Autowired
    CurrencyRepository repo;

    @Bean
    @Scheduled(cron = "0 0 0 */1 * *")
    public void fillCurrencyTable() {
        try {
            String url = "http://www.lb.lt/webservices/FxRates/FxRates.asmx/getCurrentFxRates?tp=EU";
            String xml = Server.connectAndReadXmlToString(url);
            Document document =Server.getXmlDocument(xml);
            XPath xpath = Server.getXpath();
            XPathExpression expressionForName =
                    xpath.compile("/FxRates/FxRate/CcyAmt[2]/Ccy/text()");
            XPathExpression expressionForValue =
                    xpath.compile("/FxRates/FxRate/CcyAmt[2]/Amt/text()");
            //evaluate expression result on XML document
            NodeList nodesOfNames = (NodeList) expressionForName.evaluate(document, XPathConstants.NODESET);
            NodeList nodesOfValues = (NodeList) expressionForValue.evaluate(document, XPathConstants.NODESET);
            repo.deleteAll();
            for (int i = 0; i < nodesOfNames.getLength(); i++) {
                repo.save(new Currency(nodesOfNames.item(i).getNodeValue(), Double.parseDouble(nodesOfValues.item(i).getNodeValue())));

            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }
}
