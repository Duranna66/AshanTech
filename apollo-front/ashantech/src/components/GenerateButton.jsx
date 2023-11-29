import React, {useState} from 'react';

function GenerateButton(props) {
    const [data, setData] = useState(null);
    const handleClick = () => {
        fetch('http://localhost:8080/search/generate', {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(true)
        })
            .then(response => response.json())
            .then(data => {
                setData(data);
            })
            .catch(error => {
                console.log(error)
            });
        console.log("yes")
        window.location.reload(false);
    };
    return (
        <button className={"addButton"} onClick={handleClick}>сгенерировать</button>
    );
}

export default GenerateButton;