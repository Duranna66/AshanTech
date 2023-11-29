import React, { useState } from 'react';

function SaveButton({appState, matrix}) {
    const [data, setData] = useState(null);
    let a = []

    const handleClick = () => {
        fetch('http://localhost:8080/search/update', {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(appState)
        })
            .then(response => response.json())
            .then(data => {
                setData(data);
            })
            .catch(error => {
            });
        matrix.map(x =>
            x.map(y =>
                y.map(z => {if(z[0].name !== '' && z[1].name !== '') {
                    a.push({animalId1: z[0].animal_id, animalId2: z[1].animal_id})
                }})))

        fetch('http://localhost:8080/search/count', {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(a),
        })
            .then(response => response.json())
            .then(data => {
                setData(data);
            })
            .catch(error => {
            });
        window.location.reload(false);
    };

    return (
        <div className={"bott"}>
            <button className={"addButton"} onClick={handleClick}>сохранить</button>
        </div>
    );
}

export default SaveButton;