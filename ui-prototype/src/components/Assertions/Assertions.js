import React from "react";

import comparators from "../../comparisons.json";

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

function getRandomInt(max) {
    return Math.floor(Math.random() * Math.floor(max));
}

const Assertions = () => (
    <>
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
                        <option value={val}>
                            {val} {comparators[getRandomInt(5)]} {getRandomInt(100)}
                        </option>
                    ))}
                </select>
                <button type="button">-</button>
            </div>
            <div
                style={{
                    width: "50%",
                    display: "flex",
                    flexFlow: "column nowrap",
                    alignItems: "center",
                }}
            >
                <select style={{ width: "65%" }}>
                    {alphaSet.map((val) => (
                        <option value={val}>{val}</option>
                    ))}
                </select>
                <select>
                    {comparators.map((symbol) => (
                        <option value={symbol}>{symbol}</option>
                    ))}
                </select>
                <div style={{ width: "65%", display: "flex", flexFlow: "row nowrap" }}>
                    <input type="number" style={{ flexGrow: 1 }} />
                    <button type="button">+</button>
                </div>
            </div>
        </div>
    </>
);

export default Assertions;
