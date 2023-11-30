import React, { useEffect, useState } from "react";
import axios from "axios";
import "./App.css";
import MatrixTest from "./components/Matrix2";
import Modal from "./components/Modal";


function App() {
    const [openModal, setOpenModal] = useState(false)
    const [appState, setAppState] = useState([])
    const [size, setSize] = useState(0)
    useEffect(() => {
        const apiUrl = "http://localhost:8080/search/size";
        axios.get(apiUrl).then((resp) => {
            const gotSize = resp.data;
            setSize(gotSize.size);
        });
    }, []);
    return (

        <div className="App">
            { openModal &&  <Modal setActive={setOpenModal} appState={appState} setAppState={setAppState}/>}
            <div className={"container"}>
            <MatrixTest size={size} setSize={setSize} appState={appState} setAppState={setAppState} openModal={openModal} setOpenModal={setOpenModal}></MatrixTest>
            </div>
        </div>

    );
}

export default App;