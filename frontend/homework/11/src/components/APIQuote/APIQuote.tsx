import React, { useEffect, useState } from "react";
import { RawQuote } from "../../interface";
import Header from "../Header/Header";
import Quote from "../Quote/Quote";
import "./APIQuote.scss";

function APIQuote() {
  const [allQuotes, setAllQuotes] = useState<RawQuote[]>([]);
  const [quotes, setQuotes] = useState<RawQuote[]>([]);
  const [selectedFilters, setSelectedFilters] = useState<string[]>([]);
  const [isLoading, setIsLoading] = useState<boolean>(false);

  useEffect(() => {
    setIsLoading(true);
    fetch("https://api.quotable.io/quotes/random?limit=3")
      .then((response) => response.json())
      .then((data: RawQuote[]) => {
        setAllQuotes(data);
        setIsLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching data: ", error);
      });
  }, []);

  useEffect(() => {
    if (selectedFilters.length > 0) {
      const filteredQuotes = allQuotes.filter((quote) => {
        return selectedFilters.every((filter) => quote.tags.includes(filter));
      });
      setQuotes(filteredQuotes);
    } 
    else {
      setQuotes(allQuotes);
    }
  }, [selectedFilters, allQuotes]);

  return (
    <div className="app">
      <Header
        allQuotes={allQuotes}
        setAllQuotes={setAllQuotes}
        isLoading={isLoading}
        setIsLoading={setIsLoading}
        selectedFilters={selectedFilters}
        setSelectedFilters={setSelectedFilters}
      />
      <div className="quotes">
        {quotes.map((quote) => {
          return (
            <Quote
              key={quote._id}
              quote={quote}
              selectedFilters={selectedFilters}
              setSelectedFilters={setSelectedFilters}
            />
          );
        })}
      </div>
    </div>
  );
}

export default APIQuote;
