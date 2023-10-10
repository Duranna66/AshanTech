import axios from './axios'
import {dataForSearch} from "../types";

export const getDataForSearch = (text: string): Promise<dataForSearch[]> => {
    return axios
        .get('/search', {
            params: {
                text: text,
            },
        })
        .then((res ) => res.data);
};