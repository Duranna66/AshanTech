import React, { useState } from 'react';

function Modal({ active, setActive, setAppState, appState }) {
    const [highlightedButton, setHighlightedButton] = useState(null);
    const [isPredator, setIsPredator] = useState(null)
    const [inputValue, setInputValue] = useState('');
    const handleClick = () => {
        let value = { name: inputValue, prisonId: -1, isPredator: isPredator, isDeleted: 'true' };
        fetch('http://localhost:8080/search/addAnimal', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(value)
        })
            .then(response => {
                if (!response.ok) {
                    alert("братан, сказал же уникальный :(")
                    throw new Error(response.statusText);
                }
                return response.json();
            })
            .then(data => {
                console.log(data)
            })
            .catch(error => {
                console.error(error);
            });
            setActive(false);
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
                <button className={"glow-on-hover"} onClick={() => setActive(false)}>X</button>
                <div className={"title"}>
                    <h1>Введите данные животного</h1>
                    <div className={"body"}></div>
                    <p>
                        Введите имя(уникальное)
                        <br></br>
                        <input className={"myInput"} onChange={(e) => inputChangeHandler(e)}/>
                    </p>
                    <p>
                        Хищник?
                        <br></br>
                        <button
                            className={highlightedButton === 1 ? "highlight" : "not_highlight"}
                            onClick={() => {handleButtonClick(1)
                                setIsPredator(true)
                            }}
                        >
                            yes
                        </button>
                        <button
                            className={highlightedButton === 2 ? "highlight" : "not_highlight"}
                            onClick={() => {handleButtonClick(2)
                            setIsPredator(false)}}
                        >
                            no
                        </button>
                    </p>
                </div>
                <button className={"addButton"} onClick={handleClick}>Добавить</button>
            </div>
        </div>
    );
}

export default Modal;