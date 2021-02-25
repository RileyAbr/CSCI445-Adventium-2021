import React from "react";
import { CopyToClipboard } from "react-copy-to-clipboard";

const Output = ({ assumptions, guarantees }) => {
    let formattedOutput = "annex agree{** \n";

    for (let i = 0; i < assumptions.length; i += 1) {
        formattedOutput += `${assumptions[i]}\n`;
    }

    for (let i = 0; i < guarantees.length; i += 1) {
        formattedOutput += `${guarantees[i]}\n`;
    }

    formattedOutput += "**};";

    return (
        <>
            <h2>Output</h2>
            <div
                style={{
                    display: "flex",
                    height: "60%",
                    width: "70%",
                    flexFlow: "column nowrap",
                    justifyContent: "flex-start",
                    alignItems: "center",
                    margin: "0 auto",
                }}
            >
                <textarea value={formattedOutput} style={{ width: "100%" }} rows="18" />
                <CopyToClipboard text={formattedOutput}>
                    <button type="button">Copy to Clipboard</button>
                </CopyToClipboard>
            </div>
        </>
    );
};

export default Output;
