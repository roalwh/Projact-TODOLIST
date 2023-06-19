import React from 'react'
import { TextField, Paper, Button, Grid } from "@mui/material";

function AddTodo(props) {


    return (
        <Paper style={{ margin: 10, padding: 16 }}>
            <Grid container>
                <Grid xs={11} md={11} item style={{ paddingRight: 16 }}>
                    <TextField placeholder="Add Todo here" fullWidth />
                </Grid>
                <Grid xs={1} md={1} item>
                    <Button fullWidth color="secondary" variant="outlined">
                        +
                    </Button>
                </Grid>
            </Grid>
        </Paper>
    )
}
export default AddTodo;