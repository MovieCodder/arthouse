import React from "react";

const MovieGrid = ({movies}) => {
    const filteredMovies = Object.entries(movies)
        .filter(([, films]) => films && films.length > 0)
        .slice(0, 7); // Limit to 7 days

    if (filteredMovies.length === 0) {
        return <p>No movies available</p>;
    }

    const dates = filteredMovies.map(([date]) => date);
    const maxMovies = Math.max(
        ...filteredMovies.map(([, films]) => films.length)
    );
    console.log(Object.entries(movies))

    return (
        <div className="grid-container">
            {dates.map((date, index) => (
                <div key={`date-${index}`} className="grid-item header">
                    {date}
                </div>
            ))}

            {[...Array(maxMovies)].map((_, rowIndex) =>
                dates.map((date, colIndex) => (
                    <div key={`${date}-${rowIndex}`} className="grid-item">
                        {movies[date][rowIndex] || ''}
                    </div>
                ))
            )}
        </div>
    );
};

export default MovieGrid;