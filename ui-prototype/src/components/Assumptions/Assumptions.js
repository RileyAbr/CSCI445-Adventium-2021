import React, { useState } from "react";

import ParameterSelector from "../ParameterSelector";
import SymbolSelector from "../SymbolSelector";

const Assumptions = ({ parameters, assumptions, assumptionSymbols, modifyAssumptions }) => {
    const [selectedOperand, setSelectedOperand] = useState(parameters[0]);
    const [selectedAssumptionSymbol, setSelectedAssumptionSymbol] = useState(assumptionSymbols[0]);
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
                    <ParameterSelector
                        parameters={parameters}
                        selectParameter={setSelectedOperand}
                    />

                    <SymbolSelector
                        symbols={assumptionSymbols}
                        selectSymbol={setSelectedAssumptionSymbol}
                    />

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
                                    `${selectedOperand} ${selectedAssumptionSymbol} ${addAssumptionInput}`
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
