import React from "react";
import { RawQuote } from "../../interface";
import "./Quote.scss";

interface QuoteProps {
  readonly quote: RawQuote;
  readonly selectedFilters: string[];
  readonly setSelectedFilters: React.Dispatch<React.SetStateAction<string[]>>;
}
function Quote({ quote, selectedFilters, setSelectedFilters }: QuoteProps) {
  const addFilter = (filter: string) => {
    setSelectedFilters([...selectedFilters.filter(f => f !== filter), filter]);
  }
  return (
    <div className="quote">
      <h1 className="content">{quote.content}</h1>
      <h3 className="author">~{quote.author}</h3>
      <p className="date">{quote.dateModified}</p>
      <div className="tags">
        {quote.tags.map((tag: string) => {
          return (
            <button
              key={tag}
              className="tag"
              onClick={() => addFilter(tag)}
            >
              {tag}
            </button>
          );
        })}
      </div>
    </div>
  );
}

export default Quote;
