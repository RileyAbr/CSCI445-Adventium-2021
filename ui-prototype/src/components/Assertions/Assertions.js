import React from "react";

const alphaSet = [
    "a",
    "b",
    "c",
    "d",
    "e",
    "f",
    "g",
    "h",
    "i",
    "j",
    "k",
    "l",
    "m",
    "n",
    "o",
    "p",
    "q",
    "r",
    "s",
    "t",
    "u",
    "v",
    "w",
    "x",
    "y",
    "z",
];

const Assertions = () => (
    <div>
        <h2>Assertions</h2>
        <div
            style={{
                display: "flex",
                height: "50%",
                flexFlow: "column nowrap",
                justifyContent: "space-between",
                alignItems: "center",
            }}
        >
            <div
                style={{
                    width: "50%",
                    display: "flex",
                    flexFow: "row nowrap",
                    alignItems: "center",
                }}
            >
                <select style={{ flexGrow: 1 }} size="8">
                    {alphaSet.map((val) => (
                        <option value={val}>Sample parameter {val}</option>
                    ))}
                </select>
                <button type="button">-</button>
            </div>
            <div
                style={{
                    width: "50%",
                    display: "flex",
                    flexFow: "row nowrap",
                    alignItems: "center",
                }}
            >
                <select>
                    <option value="test">Test</option>
                </select>
                <select>
                    <option value=">">{">"}</option>
                    <option value=">">{">="}</option>
                    <option value=">">=</option>
                    <option value=">">{"=<"}</option>
                    <option value=">">{"<"}</option>
                </select>
                <input type="number" />
                <button type="button">+</button>
            </div>
        </div>
    </div>
);

export default Assertions;
