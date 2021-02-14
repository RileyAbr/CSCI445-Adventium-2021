import React from "react";

const Assertions = ({ parameters, assertions, symbols }) => (
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
                    {assertions.map((val) => (
                        <option value={val}>{val}</option>
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
                    {parameters.map((val) => (
                        <option value={val}>{val}</option>
                    ))}
                </select>
                <select>
                    {symbols.map((symbol) => (
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
