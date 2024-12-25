import React, { useState, useEffect } from 'react';
import './App.css';
import DatePickerButton from "./Components/DatePicker";
import {useScraper} from "./Components/UseScraper";
import MovieGrid, {movieGrid} from "./Components/MovieGrid";

function App() {
    const muranowMovies = useScraper();
    const [selectedDate, setSelectedDate] = useState(new Date())

    return (
        <div className="App">
            <header className="App-header">
                <h1>Muranow Movies</h1>
                <DatePickerButton selectedDate = {selectedDate} onChange ={setSelectedDate}/>
                <MovieGrid movies = {muranowMovies} selectedDate ={selectedDate}></MovieGrid>
            </header>
        </div>
    );
}

export default App;
