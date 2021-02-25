import React from "react";

const SymbolSelector = ({ symbols, selectSymbol }) => (
    <select onChange={(event) => selectSymbol(event.target.value)}>
        {symbols.map((val) => (
            <option value={val}>{val}</option>
        ))}
    </select>
);

export default SymbolSelector;
