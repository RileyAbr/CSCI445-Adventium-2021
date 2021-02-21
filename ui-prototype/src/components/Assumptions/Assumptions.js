import React, { useState } from "react";

const Assumptions = ({ parameters, assumptions, symbols, modifyAssumptions }) => {
    const [addSelectedParameter, setAddSelectedParameter] = useState(parameters[0]);
    const [addSelectedComparator, setAddSelectedComparator] = useState(symbols[0]);
    const [addAssumptionInput, setAddAssumptionInput] = useState();
    const [removeAssumptionInput, setRemoveAssumptionInput] = useState();

    const addAssumption = (addValue) => {
        if (addValue) {
            const newAssumptions = [...assumptions];
            newAssumptions.push(addValue);
            modifyAssumptions(newAssumptions);
        }
    };

    const removeParameter = (removeValue) => {
        let newAssumptions = [...assumptions];
        newAssumptions = newAssumptions.filter((item) => item !== removeValue);
        modifyAssumptions(newAssumptions);
    };
    return (
        <>
            <h2>Assumptions</h2>
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
                        onChange={(event) => setRemoveAssumptionInput(event.target.value)}
                    >
                        {assumptions.map((val) => (
                            <option value={val}>{val}</option>
                        ))}
                    </select>
                    <button type="button" onClick={() => removeParameter(removeAssumptionInput)}>
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
                            onChange={(event) => setAddAssumptionInput(event.target.value)}
                        />
                        <button
                            type="button"
                            onClick={() =>
                                addAssumption(
                                    `${addSelectedParameter} ${addSelectedComparator} ${addAssumptionInput}`
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

export default Assumptions;
