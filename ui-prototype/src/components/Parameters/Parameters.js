import React from "react";

const Parameters = ({ parameters }) => (
    <>
        <h2>Parameters</h2>
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
                    {parameters.map((val) => (
                        <option value={val}>{val}</option>
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
                <input type="text" style={{ flexGrow: 1 }} />
                <button type="button">+</button>
            </div>
        </div>
    </>
);

export default Parameters;
