import React, {useEffect, useState} from 'react';
import axios from "axios";
import {FixedSizeList} from "react-window";
import app from "../App";
import SaveButton from "./SaveButton";
import DeletePrisonButton from "./DeletePrisonButton";
import DeleteButton from "./DeleteButton";
import Modal from "./Modal";
import GenerateButton from "./GenerateButton";
import InputPrison from "./InputPrison";


function MatrixTest({ size, setSize, appState, setAppState, openModal, setOpenModal }) {
    useEffect(() => {
        const apiUrl = "http://localhost:8080/search/all";
        axios.get(apiUrl).then((resp) => {
            const allPersons = resp.data;
            setAppState(allPersons);
        });
    }, []);
    let matrix = [];
    const [currentItem, setCurrentItem] = useState([])
    const [currentBoard, setCurrentBoard] = useState([])
    for (let i = 0; i < size; i++) {
        matrix.push([]);
        for (let j = 0; j < size; j++) {
            matrix[i].push(1);
        }
    }
    for (let i = 0; i < size; i++) {
        for (let j = 0; j < size; j++) {
            matrix[i][j] = [[{id:0, prison_id: i * size + j + 1, isPredator: "0", animal_id: 0, name:''}, {id:1, prison_id: i * size + j + 1, isPredator: '0', animal_id: 0, name:''}]]
        }
    }
    for(let k = 0; k < appState.length; k++) {
        for (let i = 0; i < size; i++) {
            for (let j = 0; j < size; j++) {
                let tmp = [[{id:0, prison_id: i * size + j + 1, isPredator: "0", animal_id: 0, name:''}, {id:1, prison_id: i * size + j + 1, isPredator: '0', animal_id: 0, name:''}]]
                if (appState.length > 0 && j === (appState[k].prisonId - 1) % size && i === Math.floor((appState[k].prisonId - 1) / size) && appState[k].isDeleted === 'false') {
                    let index = matrix[i][j].map(x => x.map(y => {if(y.name === '') {
                        return y.id
                    }}))
                    if(index[0][0] === 0) {
                        tmp = [[{id:0, prison_id: appState[k].prisonId, isPredator: appState[k].isPredator, animal_id: appState[k].id, name: appState[k].name}, {id:1, prison_id: matrix[i][j][0][1].prison_id, isPredator: matrix[i][j][0][1].isPredator,  animal_id: matrix[i][j][0][1].id ,name: matrix[i][j][0][1].name}]]

                    }
                    else if(index[0][1] === 1) {
                        tmp = [[{id:0, prison_id: matrix[i][j][0][0].prison_id ,isPredator: matrix[i][j][0][0].isPredator, animal_id: matrix[i][j][0][0].animal_id , name: matrix[i][j][0][0].name}, {id:1, prison_id: appState[k].prisonId, isPredator: appState[k].isPredator,  animal_id: appState[k].id, name: appState[k].name}]]
                    }
                    matrix[i][j] = tmp
                }
            }
        }
    }

    function dragOverHandler(e) {
        e.preventDefault()
    }

    function dragLeaveHandler(e) {

    }

    function dragStartHandler(e, itemr, board) {
        // console.log(itemr) //массив с данными животного, которого взяли
        setCurrentBoard(board)
        setCurrentItem(itemr);
    }

    function dragEndHandler(e) {

    }
    function dragStartHandlerList(e, x) {
        // console.log(x)
        // console.log({id: 0, phoneNumber: x.phoneNumber, isPredator: x.isPredator, animal_id: x.id, name: x.name})
        setCurrentItem([{id: 0, prisonId: x.prisonId, isPredator: x.isPredator, animal_id: x.id, name: x.name}]);
    }
    useEffect(() => {
    }, [currentItem]);

    function dropHandler(e, itemr, board) {
        // console.log("itemr",itemr)
        // console.log("currentItem",currentItem)
        // console.log("board",board)
        // console.log("currentBoard",currentBoard)
        e.preventDefault()
        // console.log('i:',itemr) //массив с данными животного, в которого положили
        // console.log('c:', currentItem) //массив с данными животного, которого взяли
        let k1 = -1 //c кем обмениваемся
        let k2 = -1;// кого обмениваем
        if(currentItem.prison_id === undefined) {
            console.log("itemr",itemr)
            console.log("currentItem",currentItem)
            console.log("board",board)
            console.log("currentBoard",currentBoard)
            for(let i = 0; i < appState.length; i++) {
                if(appState[i].id === currentItem[0].animal_id) {
                    k1 = i;
                    break;
                }
            }
            // appState[k1].isDeleted = 'false'
            // appState[k1].phoneNumber = itemr.prison_id
            if(currentItem[0].isPredator === 'true') {
                if (board[0].isPredator === 'true' || (board[0].isPredator === '0' && board[1].isPredator === '0'))
                {
                    appState[k1].isDeleted = 'false'
                    appState[k1].prisonId = itemr.prison_id
                }
                else if(board[1].isPredator === 'true' || (board[0].isPredator === '0' && board[1].isPredator === '0')) {
                    appState[k1].isDeleted = 'false'
                    appState[k1].prisonId = itemr.prison_id
                }
                else {
                    alert(board[0].name + " is not Predator")
                }
            }
            else if(currentItem[0].isPredator === 'false') {
                if (board[0].isPredator === 'false' || (board[0].isPredator === '0' && board[1].isPredator === '0')) {
                    appState[k1].isDeleted = 'false'
                    appState[k1].prisonId = itemr.prison_id
                } else if (board[0].isPredator === 'false' || (board[0].isPredator === '0' && board[1].isPredator === '0')) {
                    appState[k1].isDeleted = 'false'
                    appState[k1].prisonId = itemr.prison_id
                } else {
                    alert(board[0].name + " is Predator")
                }
            }
            setCurrentItem(itemr);
            return
        }
        for(let i = 0; i < appState.length; i++) { //с кем кладем
            if(appState[i].id === itemr.animal_id) {
                k1 = i;
                break;
            }
        }
        for(let i = 0; i < appState.length; i++) { //кого кладем
            if(appState[i].id === currentItem.animal_id) {
                k2 = i;
                break;
            }
        }
        // if(k1 !== -1) { //когда обмениваем друг с другом
        //     let tmp = appState[k1].phoneNumber;
        //     appState[k1].phoneNumber = appState[k2].phoneNumber
        //     appState[k2].phoneNumber = tmp
        // }
         if(k1 === -1 && k2 !== -1) { //itemr is empty['', 'prison_id', '']
             if(currentItem.isPredator === 'true') {
                 if (board[0].isPredator === 'true' || (board[0].isPredator === '0' && board[1].isPredator === '0'))
                 {
                     appState[k2].prisonId = itemr.prison_id
                 }
                 else if(board[1].isPredator === 'true' || (board[0].isPredator === '0' && board[1].isPredator === '0')) {
                     appState[k2].prisonId = itemr.prison_id
                 }
                 else {
                     alert(board[0].name + " is not Predator")
                 }
             }
             else if(currentItem.isPredator === 'false') {
                 if (board[0].isPredator === 'false' || (board[0].isPredator === '0' && board[1].isPredator === '0')) {
                     appState[k2].prisonId = itemr.prison_id
                 } else if (board[0].isPredator === 'false' || (board[0].isPredator === '0' && board[1].isPredator === '0')) {
                     appState[k2].prisonId = itemr.prison_id
                 } else {
                     alert(board[0].name + " is Predator")
                 }
             }
        }
        else if(k1 === -1 && k2 === -1) {

            itemr.name = currentItem.name;
            itemr.animal_id = currentItem.animal_id
        }

        setCurrentItem(itemr);
    }
    return (
        <div className="container">
            <table className="itemM">
                {matrix.map((row, rowIndex) => (
                    <tr key={`row-${rowIndex}`}>
                        {row.map((cell, colIndex) => (
                            <td className="k" key={`cell-${colIndex}`}>
                                {matrix[rowIndex][colIndex].map(x =>
                                    x.map(r => {
                                        if (r.name !== '' && r.isPredator === 'true') {
                                            return <div
                                                draggable={true}
                                                onDragOver={(e) => dragOverHandler(e, r)}
                                                onDragLeave={(e) => dragLeaveHandler(e)}
                                                onDragStart={(e) => dragStartHandler(e, r, matrix[rowIndex][colIndex][0])}
                                                onDragEnd={(e) => dragEndHandler(e)}
                                                onDrop={(e) => dropHandler(e, r, matrix[rowIndex][colIndex][0])}
                                                className={"itemsT"}>{r.name}<DeletePrisonButton className={"button"} appState={r}></DeletePrisonButton></div>;
                                        }
                                        else if(r.name !== '' && r.isPredator === 'false') {
                                            return <div
                                                draggable={true}
                                                onDragOver={(e) => dragOverHandler(e, r)}
                                                onDragLeave={(e) => dragLeaveHandler(e)}
                                                onDragStart={(e) => dragStartHandler(e, r, matrix[rowIndex][colIndex][0])}
                                                onDragEnd={(e) => dragEndHandler(e)}
                                                onDrop={(e) => dropHandler(e, r, matrix[rowIndex][colIndex][0])}
                                                className={"itemsP"}>{r.name}<DeletePrisonButton className={"button"} appState={r}></DeletePrisonButton></div>;
                                        }
                                        else {
                                            return <div className={"empty"} onDrop={(e) => dropHandler(e, r, matrix[rowIndex][colIndex][0])}
                                                        onDragOver={(e) => dragOverHandler(e)}
                                            >{r.name}</div>
                                        }
                                    })
                                )}
                            </td>
                        ))}
                    </tr>
                ))}
                <div className={"container"}>
                    <GenerateButton className={"addButton"}>сгенерировать</GenerateButton>
                <SaveButton appState={appState} matrix={matrix}></SaveButton>
                    <button className={"addButton"} onClick={() => setOpenModal(true)}>добавить</button>
                </div>
                <InputPrison setSize={setSize}></InputPrison>
            </table>
            <div className="item">
                {appState.map(x => {
                    if(x.isDeleted !== 'q' && x.isPredator === 'true') {
                        return <div className={"itemsT"}
                                     draggable={true}
                                     onDragStart={(e) => dragStartHandlerList(e,x)}
                                     onDragOver={(e) => dragOverHandler(e)}
                                >
                                    {x.name}
                                    <DeleteButton appState={x}>x</DeleteButton>
                                </div>
                    }
                    else if(x.isDeleted !== 'q' && x.isPredator === 'false') {
                        return <div className={"itemsP"}
                                    draggable={true}
                                    onDragStart={(e) => dragStartHandlerList(e,x)}
                                    onDragOver={(e) => dragOverHandler(e)}
                        >
                            {x.name}
                            <DeleteButton appState={x}>x</DeleteButton>
                        </div>
                    }
                    else {
                        return null;
                    }
                    }

                    // x.isDeleted !== 'q' && x.isPredator === 'true' ? (
                    //     <div className={"itemsP"}
                    //          draggable={true}
                    //          onDragStart={(e) => dragStartHandlerList(e,x)}
                    //          onDragOver={(e) => dragOverHandler(e)}
                    //     >
                    //         {x.name}
                    //         <DeleteButton appState={x}>x</DeleteButton>
                    //     </div>
                    // ) : null
                )}
            </div>
        </div>
    );
}

export default MatrixTest;
