import React from "react";

const AgreeDescInput = ({ setDescription }) => (
    <input
        type="text"
        style={{ width: "100%" }}
        onChange={(event) => setDescription(event.target.value)}
    />
);

export default AgreeDescInput;
