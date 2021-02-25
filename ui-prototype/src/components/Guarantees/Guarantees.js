import React, { useState } from "react";

import ParameterSelector from "../ParameterSelector";

const Guarantees = ({ parameters, guarantees, symbols, modifyGuarantees }) => {
    const [selectedConditionalOperand, setSelectedConditionalOperand] = useState(parameters[0]);
    const [selectedResultOperand, setSelectedResultOperand] = useState(parameters[0]);
    const [addSelectedComparator, setAddSelectedComparator] = useState(symbols[0]);
    const [addGuaranteeInput, setAddGuaranteeInput] = useState();
    const [removeGuaranteeInput, setRemoveGuaranteeInput] = useState();

    const selectResultOperand = (resultValue) => {
        setSelectedResultOperand(resultValue);
    };

    const addGuarantee = (addValue) => {
        if (addValue) {
            const newGuarantees = [...guarantees];
            newGuarantees.push(addValue);
            modifyGuarantees(newGuarantees);
        }
    };

    const removeGuarantee = (removeValue) => {
        let newGuarantees = [...guarantees];
        newGuarantees = newGuarantees.filter((item) => item !== removeValue);
        modifyGuarantees(newGuarantees);
    };

    return (
        <>
            <h2>Guarantees</h2>
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
                        onChange={(event) => setRemoveGuaranteeInput(event.target.value)}
                    >
                        {guarantees.map((val) => (
                            <option value={val}>{val}</option>
                        ))}
                    </select>
                    <button type="button" onClick={() => removeGuarantee(removeGuaranteeInput)}>
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
                    {/* <select
                        style={{ width: "65%" }}
                        onChange={(event) => setSelectedConditionalOperand(event.target.value)}
                    >
                        {parameters.map((val) => (
                            <option value={val}>{val}</option>
                        ))}
                    </select> */}

                    <ParameterSelector
                        parameters={parameters}
                        selectParameter={setSelectedConditionalOperand}
                    />

                    <select onChange={(event) => setAddSelectedComparator(event.target.value)}>
                        {symbols.map((symbol) => (
                            <option value={symbol}>{symbol}</option>
                        ))}
                    </select>

                    <ParameterSelector
                        parameters={parameters}
                        selectParameter={selectResultOperand}
                    />

                    <div style={{ width: "65%", display: "flex", flexFlow: "row nowrap" }}>
                        <input
                            type="number"
                            style={{ flexGrow: 1 }}
                            onChange={(event) => setAddGuaranteeInput(event.target.value)}
                        />
                        <button
                            type="button"
                            onClick={() =>
                                addGuarantee(
                                    `${selectedConditionalOperand} ${addGuaranteeInput} ${addSelectedComparator} ${selectedResultOperand}`
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

export default Guarantees;
