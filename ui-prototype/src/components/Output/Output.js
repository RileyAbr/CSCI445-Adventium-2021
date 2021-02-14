import React from "react";

const Output = ({ assertions }) => {
    let formattedOutput = "";

    for (let i = 0; i < assertions.length; i += 1) {
        formattedOutput += `${assertions[i]} \n`;
    }

    return (
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
                <textarea value={formattedOutput} style={{ width: "100%" }} rows="18" />
                <button type="button">Copy to Clipboard</button>
            </div>
        </>
    );
};

export default Output;
