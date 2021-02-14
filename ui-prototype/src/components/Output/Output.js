import React from "react";

const lorem = `Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.`;

const Output = () => (
    <>
        <h2>Output</h2>
        <div
            style={{
                display: "flex",
                height: "50%",
                width: "50%",
                flexFlow: "column nowrap",
                justifyContent: "flex-start",
                alignItems: "center",
                margin: "0 auto",
            }}
        >
            <textarea value={lorem} style={{ width: "100%" }} rows="18" />
            <button type="button">Copy to Clipboard</button>
        </div>
    </>
);

export default Output;
