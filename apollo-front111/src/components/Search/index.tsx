import {useEffect, useState} from "react";
import {dataForSearch} from "../../types";
import {getDataForSearch} from "../../utils/http.Services";



function Search () {
    const [text, setText] = useState<string>('')
    const [dataForSearch, setDataForSearch] = useState<dataForSearch[]>([])

    useEffect(() => {
        setDataForSearch([])
    }, [])

    const handleSubmit = (e) => {
        setText(e.preventDefault)
        getDataForSearch(text).then(data => setDataForSearch(data))
    }

    const getFilteredItems = (text, dataForSearch) => {
        if (!text) {
            return dataForSearch
        }
        return dataForSearch.filter(data => data.type.includes(text) || data.firm.includes(text) || data.flavor.includes(text))
    }

    const filteredSearchData = getFilteredItems(text, dataForSearch)

    return (
        <div>
            <label>
                <input type="text"
                       value={text}
                       onChange={(e) => setText(e.target.value)}
                />
                <input type="submit" onClick={(e) => handleSubmit(e)}/>
                <ul>
                    {filteredSearchData.map((item) =>
                        <h1 key={item.name}>{item.name}</h1>
                    )}
                </ul>
            </label>
        </div>
    );
}
export default Search;