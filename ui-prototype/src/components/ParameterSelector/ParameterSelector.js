import React from "react";

const ParameterSelector = ({ parameters, selectParameter, width }) => (
    <select
        style={{ width: width || "65%" }}
        onChange={(event) => selectParameter(event.target.value)}
    >
        {parameters.map((val) => (
            <option value={val}>{val}</option>
        ))}
    </select>
);

export default ParameterSelector;
