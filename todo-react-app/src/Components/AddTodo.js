import React, { useState } from 'react'
import { TextField, Paper, Button, Grid } from "@mui/material";


function AddTodo(props) {
    const [item, setItem] = new useState({ title: "" });


    // title 값을 가져오는 함수 생성
    const onInputChange = (e) => {
        setItem({ title: e.target.value });
        
    }


    const enterKeyEventHandler = (e) => {
        if (e.key === 'Enter') {
            onButtonClick();
        }
    }

    // Add 함수 작성
    const onButtonClick = () => {
        props.Add(item);
        // 입력후 초기화
        setItem({ title: "" });
    }
    


    return (
        <Paper style={{ margin: 10, padding: 16 }}>
            <Grid container>
                <Grid xs={11} md={11} item style={{ paddingRight: 16 }}>
                    <TextField onKeyPress={enterKeyEventHandler} 
                        placeholder="Add Todo here"
                        fullWidth
                        onChange={onInputChange}
                        value={item.title}
                    />
                </Grid>
                <Grid xs={1} md={1} item>
                    <Button fullWidth color="secondary" variant="outlined"
                        onClick={onButtonClick}>
                        +
                    </Button>
                </Grid>
            </Grid>
        </Paper>
    )
}
export default AddTodo;