import React, { useState } from 'react'

import {
    ListItem,
    ListItemText,
    InputBase,
    Checkbox,
    ListItemSecondaryAction,
    IconButton,
} from "@mui/material";
import DeleteOutlined from "@mui/icons-material/DeleteOutline"

function Todo(props) {
    const [item, setItem] = useState(props.item);
    // 읽기전용 설정
    const [readOnly, setReadOnly] = useState(true);

    // App의 deleteItem(item);
    const deleteItem = props.deleteItem;

    // App의 editItem()
    const editItem = props.editItem;
    
    // 삭제
    const DeleteEventHandler= ()=>{
        deleteItem(item);
    }
    //읽기전용해제
    const turnoffReadOnly = () =>{
        setReadOnly(false);
    }
    // 읽기전용 적용
    const turnOnReadOnly=(e)=>{
        if(e.key === "Enter"){
            setReadOnly(true);
        }
    }
    // 읽기전용 상태를 해제하여 수정한 제목이 저장
    const editEventHandler = (e) =>{
        item.title = e.target.value;
        editItem();
    }
    const checkboxEventHandler = (e) =>{
        item.done = e.target.checked;
        editItem();
    }
    
    

    return (
        <ListItem>
            <Checkbox checked={item.done} onChange={checkboxEventHandler}></Checkbox>
            <ListItemText>
                <InputBase
                    inputProps={{"aria-label":"naked", readOnly : readOnly}}
                    type='text'
                    id={item.id}
                    name={item.id}
                    value={item.title}
                    multiline={true}
                    fullWidth={true}
                    onClick={turnoffReadOnly}
                    onKeyDown={turnOnReadOnly}
                    onChange={editEventHandler}
                >
                </InputBase>
            </ListItemText>
            <ListItemSecondaryAction>
                <IconButton aria-label='Delete Todo' onClick={DeleteEventHandler}>
                    <DeleteOutlined />
                </IconButton>
            </ListItemSecondaryAction>
        </ListItem>

    )
}
export default Todo;