import React from "react";

const Assertions = () => (
    <div>
        <h2>Assertions</h2>
        <div>
            <select>
                <option value="test">Test</option>
            </select>
            <select>
                <option value=">">{">"}</option>
                <option value=">">{">="}</option>
                <option value=">">=</option>
                <option value=">">{"=<"}</option>
                <option value=">">{"<"}</option>
            </select>
            <input type="number" />
            <button type="button">+</button>
        </div>
    </div>
);

export default Assertions;
