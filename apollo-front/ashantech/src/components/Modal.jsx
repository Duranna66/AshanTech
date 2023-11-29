import React, { useState } from 'react';

function Modal({ active, setActive, setAppState, appState }) {
    const [highlightedButton, setHighlightedButton] = useState(null);
    const [isPredator, setIsPredator] = useState(null)
    const [inputValue, setInputValue] = useState('');
    const handleClick = () => {

        // for(let i = 0; i < appState.size; i++) {
        //     console.log(appState[i], inputValue)
        //     if(appState[i] === inputValue) {
        //         alert("Сделай уникальное пж")
        //         return
        //     }
        // }

            let value = {name: inputValue, prisonId:-1, isPredator:isPredator, isDeleted:'true'}
            fetch('http://localhost:8080/search/addAnimal', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(value)
            })
                .then(response => response.json())
                .then(data => {
                    setAppState([...appState], data);
                })
                .catch(error => {
                    console.log(error);
                });
        setActive(false)
        window.location.reload(false);
    };
    const handleButtonClick = (buttonIndex) => {
        if (highlightedButton === buttonIndex) {
            setHighlightedButton(null);
        } else {
            setHighlightedButton(buttonIndex);
        }
    };
    const inputChangeHandler = (event) => {
        setInputValue(event.target.value);
    };


    return (
        <div className={"modal_background"}>
            <div className={"modal_content"}>
                <button onClick={() => setActive(false)}>X</button>
                <div className={"title"}>
                    <h1>Введите данные животного</h1>
                    <div className={"body"}></div>
                    <p>
                        Введите имя(уникальное)
                        <br></br>
                        <input onChange={(e) => inputChangeHandler(e)}/>
                    </p>
                    <p>
                        Хищник?
                        <br></br>
                        <button
                            className={highlightedButton === 1 ? "highlight" : ""}
                            onClick={() => {handleButtonClick(1)
                                setIsPredator(true)
                            }}
                        >
                            yes
                        </button>
                        <button
                            className={highlightedButton === 2 ? "highlight" : ""}
                            onClick={() => {handleButtonClick(2)
                            setIsPredator(false)}}
                        >
                            no
                        </button>
                    </p>
                </div>
                <button onClick={handleClick}>Добавить</button>
            </div>
        </div>
    );
}

export default Modal;