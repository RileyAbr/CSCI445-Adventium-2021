import React, { useState } from "react";

const Parameters = ({ parameters, modifyParameters }) => {
    const [addParameterInput, setAddParameterInput] = useState();
    const [removeParameterInput, setRemoveParameterInput] = useState();

    const addParameter = (addValue) => {
        if (addValue) {
            const newParameters = [...parameters];
            newParameters.push(addValue);
            modifyParameters(newParameters);
        }
    };

    const removeParameter = (removeValue) => {
        let newParameters = [...parameters];
        newParameters = newParameters.filter((item) => item !== removeValue);
        modifyParameters(newParameters);
    };

    return (
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
                    <select
                        style={{ flexGrow: 1 }}
                        size="8"
                        onChange={(event) => setRemoveParameterInput(event.target.value)}
                    >
                        {parameters && parameters.map((val) => <option value={val}>{val}</option>)}
                    </select>
                    <button type="button" onClick={() => removeParameter(removeParameterInput)}>
                        -
                    </button>
                </div>
                <div
                    style={{
                        width: "50%",
                        display: "flex",
                        flexFow: "row nowrap",
                        alignItems: "center",
                    }}
                >
                    <input
                        type="text"
                        style={{ flexGrow: 1 }}
                        onChange={(event) => setAddParameterInput(event.target.value)}
                    />
                    <button type="button" onClick={() => addParameter(addParameterInput)}>
                        +
                    </button>
                </div>
            </div>
        </>
    );
};

export default Parameters;
