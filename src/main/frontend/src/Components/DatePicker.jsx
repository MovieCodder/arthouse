import React, {useState} from "react";

function DatePickerButton({selectedDate, onChange}){


    const handlePreviousDay = () => {

        onChange((prevDate) => {
            const  newDate = new Date(prevDate);
            newDate.setDate(prevDate.getDate() -1)
            return newDate
        })
    }


    return (
        <div>
        <button onClick = {handlePreviousDay}>
            &lt;
        </button>
            <span>{selectedDate.toDateString()}</span>
        </div>
    )
}


export default DatePickerButton