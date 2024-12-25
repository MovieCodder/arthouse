import {useState,useEffect} from "react";


export function useScraper(){
    const [muranowFilms, setMuranowFilms] = useState({});

    useEffect(() => {
        fetch('http://localhost:8080/muranow').then((response) => {
            if(!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json()
        }).then(data => setMuranowFilms(data))
            .catch((error)=> console.log("error fetching ", error))

    }, []);
    return muranowFilms

}