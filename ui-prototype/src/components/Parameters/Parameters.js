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

const Parameters = () => (
    <>
        <h2>Parameters</h2>
        <div>
            <select size="8">
                {alphaSet.map((val) => (
                    <option value={val}>Sample parameter {val}</option>
                ))}
            </select>
            <button type="button">-</button>
        </div>
        <div>
            <input type="text" />
            <button type="button">+</button>
        </div>
    </>
);

export default Parameters;
