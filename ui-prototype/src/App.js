import React, { useState, useEffect } from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import "./App.css";

import NavButton from "./components/NavButton";
import Parameters from "./components/Parameters";
import Assertions from "./components/Assertions";
import Output from "./components/Output";

import sampleParameters from "./data_files/sampleParameters.json";
import assumptionComparators from "./data_files/assumptionComparators.json";
import guaranteeComparators from "./data_files/guaranteeComparators.json";

function getRandomInt(max) {
    return Math.floor(Math.random() * Math.floor(max));
}

function App() {
    const [parameters, setParameters] = useState(sampleParameters);
    const [assertions, setAssertions] = useState(
        sampleParameters
            .slice(0, getRandomInt(sampleParameters.length - 1) + 1)
            .map((val) => `${val} ${assumptionComparators[getRandomInt(5)]} ${getRandomInt(100)}`)
    );

    const modifyParameters = (newParameters) => {
        setParameters(newParameters);
    };

    const modifyAssertions = (newAssertions) => {
        setAssertions(newAssertions);
    };

    useEffect(() => {
        let validAssertions = [...assertions];
        validAssertions = validAssertions.filter((item) => parameters.includes(item.split(" ")[0]));
        setAssertions(validAssertions);
    }, [parameters]);

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
                                <Output assertions={assertions} />
                            </Route>
                            <Route path="/assertions">
                                <Assertions
                                    parameters={parameters}
                                    assertions={assertions}
                                    symbols={assumptionComparators}
                                    modifyAssertions={modifyAssertions}
                                />
                            </Route>
                            <Route path="/">
                                <Parameters
                                    parameters={parameters}
                                    modifyParameters={modifyParameters}
                                />
                            </Route>
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
                                <NavButton to="assertions">Back</NavButton>
                                <NavButton>Finish</NavButton>
                            </Route>
                            <Route path="/assertions">
                                <NavButton to="parameters">Back</NavButton>
                                <NavButton to="output">Next</NavButton>
                            </Route>
                            <Route path="/">
                                <NavButton disabled>Back</NavButton>
                                <NavButton to="assertions">Next</NavButton>
                            </Route>
                        </Switch>
                    </footer>
                </main>
            </div>
        </Router>
    );
}

export default App;
