package com.company.currencyTask;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


@org.springframework.stereotype.Service
class Service {

    private static final String LINK = "https://www.lb.lt/webservices/FxRates/FxRates.asmx/getFxRatesForCurrency?tp=EU&ccy=%s&dtFrom=%s&dtTo=%s";

    Result getFinalResult(String currency, String dateFrom, String dateTo) {

        Result result = new Result();
        List<CurrencyInfo> currencyList = getCurrencyInfo(currency, dateFrom, dateTo);

        result.setCurrencyInfoList(currencyList);
        result.setExchangeRateDifference(exchangeRateDifference(currencyList));

        return result;
    }


    private List<CurrencyInfo> getCurrencyInfo(String currency, String dateFrom, String dateTo) {

        List<CurrencyInfo> currencyInfoList = null;

        String url = String.format(LINK, currency, dateFrom, dateTo);

        try {
            URLConnection openConnection = new URL(url).openConnection();
            openConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            openConnection.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(openConnection.getInputStream(), Charset.forName("UTF-8")));
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(new InputSource(new StringReader(stringBuilder.toString())));
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("FxRate");

            currencyInfoList = new ArrayList<>();

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    CurrencyInfo rate = new CurrencyInfo(
                            elem.getElementsByTagName("Dt").item(0).getTextContent(),
                            elem.getElementsByTagName("Ccy").item(1).getTextContent(),
                            Double.parseDouble(elem.getElementsByTagName("Amt").item(1).getTextContent())
                    );
                    currencyInfoList.add(rate);
                }
            }

            for (int i = 0; i < currencyInfoList.size(); i++) {
                System.out.println(currencyInfoList.get(i));

            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return currencyInfoList;
    }


    private String exchangeRateDifference(List<CurrencyInfo> currencyInfoList) {
        double currentExRate = currencyInfoList.get(0).getRate();
        double lastExRate = currencyInfoList.get(currencyInfoList.size() - 1).getRate();
        NumberFormat formatter = new DecimalFormat("#0.00");
        return (formatter.format((lastExRate - currentExRate) / currentExRate * 100)) + "%";
    }


}
