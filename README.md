# LB_FXRates_back-end

This is a rest api, which takes data from official Lithuania's bank page (https://www.lb.lt/lt/kasdien-skelbiami-euro-ir-uzsienio-valiutu-santykiai-skelbia-europos-centrinis-bankas).

For testing I recommend to use Postman (or similar tools), the app takes 3 arguments: currency (ex. USD), dateFrom (ex. 2019-09-01) and dateTo (ex. 2019-09-11).

It returns an object which includes a list of objects with information and the exchange rate difference from beginning till the end of the period entered.

Please note that www.lb.lt does not publish exchange rates on a weekend.
