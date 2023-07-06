import React from 'react'

import { BrowserRouter, Routes, Route } from "react-router-dom";
import { Typography, Box } from "@mui/material";
import App from './page/App';
import Login from './page/Login';
import SignUp from './page/SignUp';

function Copyright() {
    return (
        <Typography variant='body2' color="textSecondary" align='center'>
            {"Copyright © "}
            roalwhTodo,{new Date().getFullYear()}
            {"."}
        </Typography>
    );
}


function AppRouter() {
    return (
        <div>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<App/>} />
                    <Route path="login" element={<Login/>} />
                    <Route path="SignUp" element={<SignUp/>} />
                </Routes>
            </BrowserRouter>
            <Box mt={5}>
                <Copyright />
            </Box>
        </div>
    );
};
export default AppRouter;