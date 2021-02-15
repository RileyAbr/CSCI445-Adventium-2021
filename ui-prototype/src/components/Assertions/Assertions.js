import React, { useState } from "react";

const Assertions = ({ parameters, assertions, symbols, modifyAssertions }) => {
    const [addSelectedParameter, setAddSelectedParameter] = useState();
    const [addSelectedComparator, setAddSelectedComparator] = useState();
    const [addAssertionInput, setAddAssertionInput] = useState();
    const [removeAssertionInput, setRemoveAssertionInput] = useState();

    const addAssertion = (addValue) => {
        if (addValue) {
            const newAssertions = [...assertions];
            newAssertions.push(addValue);
            modifyAssertions(newAssertions);
        }
    };

    const removeParameter = (removeValue) => {
        let newAssertions = [...assertions];
        newAssertions = newAssertions.filter((item) => item !== removeValue);
        modifyAssertions(newAssertions);
    };
    return (
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
                    <select
                        style={{ flexGrow: 1 }}
                        size="8"
                        onChange={(event) => setRemoveAssertionInput(event.target.value)}
                    >
                        {assertions.map((val) => (
                            <option value={val}>{val}</option>
                        ))}
                    </select>
                    <button type="button" onClick={() => removeParameter(removeAssertionInput)}>
                        -
                    </button>
                </div>
                <div
                    style={{
                        width: "50%",
                        display: "flex",
                        flexFlow: "column nowrap",
                        alignItems: "center",
                    }}
                >
                    <select
                        style={{ width: "65%" }}
                        onChange={(event) => setAddSelectedParameter(event.target.value)}
                    >
                        {parameters.map((val) => (
                            <option value={val}>{val}</option>
                        ))}
                    </select>
                    <select onChange={(event) => setAddSelectedComparator(event.target.value)}>
                        {symbols.map((symbol) => (
                            <option value={symbol}>{symbol}</option>
                        ))}
                    </select>
                    <div style={{ width: "65%", display: "flex", flexFlow: "row nowrap" }}>
                        <input
                            type="number"
                            style={{ flexGrow: 1 }}
                            onChange={(event) => setAddAssertionInput(event.target.value)}
                        />
                        <button
                            type="button"
                            onClick={() =>
                                addAssertion(
                                    `${addSelectedParameter} ${addSelectedComparator} ${addAssertionInput}`
                                )
                            }
                        >
                            +
                        </button>
                    </div>
                </div>
            </div>
        </>
    );
};

export default Assertions;
