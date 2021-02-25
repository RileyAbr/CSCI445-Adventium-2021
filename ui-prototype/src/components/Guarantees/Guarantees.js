import React, { useState } from "react";

import ParameterSelector from "../ParameterSelector";
import SymbolSelector from "../SymbolSelector";

const Guarantees = ({
    parameters,
    guarantees,
    assumptionSymbols,
    guaranteeSymbols,
    modifyGuarantees,
}) => {
    const [selectedConditionalOperand, setSelectedConditionalOperand] = useState(parameters[0]);
    const [selectedResultOperand, setSelectedResultOperand] = useState(parameters[0]);
    const [selectedAssumptionSymbol, setSelectedAssumptionSymbol] = useState(assumptionSymbols[0]);
    const [selectedGuaranteeSymbol, setSelectedGuaranteeSymbol] = useState(guaranteeSymbols[0]);
    const [selectAssumptionValue, setSelectAssumptionValue] = useState();
    const [removeGuaranteeInput, setRemoveGuaranteeInput] = useState();

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
                    height: "55%",
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
                    <ParameterSelector
                        parameters={parameters}
                        selectParameter={setSelectedConditionalOperand}
                    />

                    <SymbolSelector
                        symbols={assumptionSymbols}
                        selectSymbol={setSelectedAssumptionSymbol}
                    />

                    <input
                        type="number"
                        onChange={(event) => setSelectAssumptionValue(event.target.value)}
                    />

                    <SymbolSelector
                        symbols={guaranteeSymbols}
                        selectSymbol={setSelectedGuaranteeSymbol}
                    />

                    <div style={{ width: "65%", display: "flex", flexFlow: "row nowrap" }}>
                        <ParameterSelector
                            parameters={parameters}
                            selectParameter={setSelectedResultOperand}
                            width="100%"
                        />
                        <button
                            type="button"
                            onClick={() =>
                                addGuarantee(
                                    `(${selectedConditionalOperand} ${selectedAssumptionSymbol} ${selectAssumptionValue}) ${selectedGuaranteeSymbol}  ${selectedResultOperand}`
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
