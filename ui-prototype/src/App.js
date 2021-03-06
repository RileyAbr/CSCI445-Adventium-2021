import React, { useState } from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import "./App.css";

import NavButton from "./components/NavButton";
// import Parameters from "./components/Parameters";
import Assumptions from "./components/Assumptions";
import Guarantees from "./components/Guarantees";
import Output from "./components/Output";

import sampleParameters from "./data_files/sampleParameters.json";
import assumptionComparators from "./data_files/assumptionComparators.json";
import guaranteeComparators from "./data_files/guaranteeComparators.json";

function getRandomInt(max) {
    return Math.floor(Math.random() * Math.floor(max));
}

function App() {
    // eslint-disable-next-line no-unused-vars
    const [parameters, setParameters] = useState(sampleParameters);
    const [assumptions, setAssumptions] = useState(
        sampleParameters.slice(0, getRandomInt(sampleParameters.length - 1) + 1).map(
            (val) =>
                // prettier-ignore
                `\tassume "Sample assumption" : \n\t\t(${val} ${assumptionComparators[getRandomInt(assumptionComparators.length - 1)]
                } ${getRandomInt(100)})`
        )
    );
    const [guarantees, setGuarantees] = useState(
        sampleParameters.slice(0, getRandomInt(3) + 1).map(
            (val) =>
                // prettier-ignore
                `\tguarantee "This is an example guarantee." : \n\t\t(${val} ${assumptionComparators[getRandomInt(assumptionComparators.length - 1)]} ${getRandomInt(100)}) ${guaranteeComparators[getRandomInt(guaranteeComparators.length - 1)]} ${sampleParameters[getRandomInt(sampleParameters.length - 1)]};`
        )
    );

    // const modifyParameters = (newParameters) => {
    //     setParameters(newParameters);
    // };

    const modifyAssumptions = (newAssumptions) => {
        setAssumptions(newAssumptions);
    };

    const modifyGuarantees = (newGuarantees) => {
        setGuarantees(newGuarantees);
    };

    return (
        <Router>
            <div
                style={{
                    width: "100vw",
                    height: "100vh",
                    minHeight: "100vh",
                    display: "flex",
                    flexFlow: "column nowrap",
                    placeContent: "center",
                    alignItems: "center",
                    background: "#ffffff",
                }}
            >
                <h1 style={{ marginTop: 0 }}>Adventium Labs GUMBO Plugin Prototype</h1>
                <main
                    style={{
                        display: "flex",
                        flexFlow: "column nowrap",
                        justifyContent: "space-between",
                        height: "68vh",
                        width: "40%",
                        background: "#f0f0f0",
                        border: "1px solid grey",
                        boxShadow: "0px 0px 7px 3px rgba(0,0,0,0.3)",
                    }}
                >
                    <header
                        style={{
                            display: "flex",
                            flexFlow: "row nowrap",
                            justifyContent: "flex-end",
                            background: "#ffffff",
                            height: "32px",
                            borderBottom: "1px solid grey",
                        }}
                    >
                        <button type="button" className="close">
                            X
                        </button>
                    </header>
                    <article style={{ flexGrow: 1 }}>
                        <Switch>
                            <Route path="/output">
                                <Output assumptions={assumptions} guarantees={guarantees} />
                            </Route>
                            <Route path="/guarantees">
                                <Guarantees
                                    parameters={parameters}
                                    guarantees={guarantees}
                                    assumptionSymbols={assumptionComparators}
                                    guaranteeSymbols={guaranteeComparators}
                                    symbols={guaranteeComparators}
                                    modifyGuarantees={modifyGuarantees}
                                />
                            </Route>
                            <Route path="/">
                                <Assumptions
                                    parameters={parameters}
                                    assumptions={assumptions}
                                    assumptionSymbols={assumptionComparators}
                                    modifyAssumptions={modifyAssumptions}
                                />
                            </Route>
                            {/* <Route path="/">
                                <Parameters
                                    parameters={parameters}
                                    modifyParameters={modifyParameters}
                                />
                            </Route> */}
                        </Switch>
                    </article>
                    <footer
                        style={{
                            display: "flex",
                            flexFlow: "row nowrap",
                            justifyContent: "flex-end",
                            alignItems: "center",
                            borderTop: "1px solid grey",
                            height: "50px",
                        }}
                    >
                        <Switch>
                            <Route path="/output">
                                <NavButton to="guarantees">Back</NavButton>
                                <NavButton to="/">Finish</NavButton>
                            </Route>
                            <Route path="/guarantees">
                                <NavButton to="assumptions">Back</NavButton>
                                <NavButton to="output">Next</NavButton>
                            </Route>
                            <Route path="/">
                                <NavButton to="/" disabled>
                                    Back
                                </NavButton>
                                <NavButton to="guarantees">Next</NavButton>
                            </Route>
                            {/* <Route path="/">
                                <NavButton disabled>Back</NavButton>
                                <NavButton to="assumptions">Next</NavButton>
                            </Route> */}
                        </Switch>
                    </footer>
                </main>
            </div>
        </Router>
    );
}

export default App;
