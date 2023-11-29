import React, {useState} from 'react';


function DeletePrisonButton({appState}) {
    const [data, setData] = useState(null);

    const handleClick = () => {
        const animal = {id: appState.animal_id, name: appState.name, isPredator: appState.isPredator, prisonId: appState.prison_id, isDeleted: 'false'}
        fetch('http://localhost:8080/search/deleteFromPrison', {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(animal)
        })
            .then(response => response.json())
            .then(data => {
                setData(data);
            })
            .catch(error => {
            });

        window.location.reload(false);
        console.log(animal)
    };
    return (
        <button className={"button"} onClick={handleClick} >X</button>
    );
}

export default DeletePrisonButton;