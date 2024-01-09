package org.example;

public class APIResponseParser {
    /**
     * Parses the input text and returns a Book instance containing
     * the parsed data.
     * @param response text to be parsed
     * @return Book instance containing parsed data
     */
    public static Book parse(String response) {
        Book book = new Book();
        String endRule = "</title>";
        String startRule = "<title>";
        String title = parse(response, startRule, endRule);
        book.setTitle(title);

        startRule = "<name>";
        endRule = "</name>";
        String name = parse(response, startRule, endRule);
        book.setAuthor(name);

        startRule = "<original_publication_year type=\"integer\">";
        endRule = "</original_publication_year>";
        String publicationYear = parse(response, startRule, endRule);
        book.setPublicationYear(Integer.parseInt(publicationYear));

        startRule = "<average_rating>";
        endRule = "</average_rating>";
        String avgRating = parse(response, startRule, endRule);
        book.setAverageRating(Double.parseDouble(avgRating));

        startRule = "<ratings_count type=\"integer\">";
        endRule = "</ratings_count>";
        String ratingCount = parse(response, startRule, endRule);
        book.setRatingsCount(Integer.parseInt(removeComma(ratingCount)));

        startRule = "<image_url>";
        endRule = "</image_url>";
        String imgURL = parse(response, startRule, endRule);
        book.setImageUrl(imgURL);

        return book;
    }

    /**
     * finds the word between opening and closing tag which is to be extracted
     * @param response text to be parsed
     * @param startRule opening tag to be searched
     * @param endRule closing tag to be searched
     * @return String which contains the extracted word
     */
    public static String parse(String response, String startRule, String endRule) {
        int startIndex = response.indexOf(startRule);
        startIndex += startRule.length();
        int endIndex = response.indexOf(endRule);
        return response.substring(startIndex,endIndex);
    }

    /**
     * removes comma from the String which contains rating count(including commas)
     * @param ratingCount of the book in String format
     * @return String which contains only numbers
     */
    public static String removeComma(String ratingCount){
        return ratingCount.replaceAll(",","");
    }

    public static void main(String[] args) {
        Logging logging = new Logging();
        String response = "<work>" +
                "<id type=\"integer\">2361393</id>" +
                "<books_count type=\"integer\">813</books_count>" +
                "<ratings_count type=\"integer\">1,16,315</ratings_count>" +
                "<text_reviews_count type=\"integer\">3439</text_reviews_count>" +
                "<original_publication_year type=\"integer\">1854</original_publication_year>" +
                "<original_publication_month type=\"integer\" nil=\"true\"/>" +
                "<original_publication_day type=\"integer\" nil=\"true\"/>" +
                "<average_rating>3.79</average_rating>" +
                "<best_book type=\"Book\">" +
                "<id type=\"integer\">16902</id>" +
                "<title>Walden</title>" +
                "<author>" +
                    "<id type=\"integer\">10264</id>" +
                    "<name>Henry David Thoreau</name>" +
                 "</author>" +
                "<image_url>" +
                    "http://images.gr-assets.com/books/1465675526m/16902.jpg" +
                "</image_url>" +
                "<small_image_url>" +
                    "http://images.gr-assets.com/books/1465675526s/16902.jpg" +
                "</small_image_url>" +
                "</best_book>" +
                "</work>";
        Book extractedBook = APIResponseParser.parse(response);
        logging.logInfo("Printing book details");
        logging.logInfo("Title: " + extractedBook.getTitle());
        logging.logInfo("Author: " + extractedBook.getAuthor());
        logging.logInfo("Publication Year: " + extractedBook.getPublicationYear());
        logging.logInfo("Average Rating: " + extractedBook.getAverageRating());
        logging.logInfo("Rating Count: " + extractedBook.getRatingsCount());
        logging.logInfo("Image URL: " + extractedBook.getImageUrl());
    }
}
