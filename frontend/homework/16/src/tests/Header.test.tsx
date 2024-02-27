import { beforeEach, describe, expect, test } from "vitest";
import { render, fireEvent, screen } from "@testing-library/react";

import { Provider } from "react-redux";
import Header from "../components/Header/Header";
import { store } from "../redux/store";

describe("Header component", () => {
    beforeEach(() => {
        render(
            <Provider store={store}>
                <Header />
            </Provider>
        );
    });
    test("render title and search bar", () => {
        expect(screen.getByText("Item Lister")).toBeTruthy();
        expect(screen.getByRole("searchbox")).toBeTruthy();
    });

    test("search bar updates value on change", () => {
        const searchInput = screen.getByRole("searchbox");
        fireEvent.change(searchInput, { target: { value: "Search" } });
        expect(searchInput).toHaveProperty("value", "Search");
    });
});