import React from 'react'
import {ListItem,ListItemText,InputBase,Checkbox} from "@mui/material"

function Todo(props) {
    
    const item = props.item;
    return (
        <ListItem>
            <Checkbox checked={item.done}></Checkbox>
            <ListItemText>
                <InputBase
                    inputProrps={{"aria-label":"naked"}}
                    type='text'
                    name={item.id}
                    value={item.title}
                    multiline={true}
                    fullWidth={true}
                    >
                </InputBase>
            </ListItemText>
        </ListItem>

    )
}
export default Todo;