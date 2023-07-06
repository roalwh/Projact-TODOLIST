import { AppBar, Button, Grid, Toolbar, Typography } from '@mui/material'
import React from 'react'
import { signout } from '../service/ApiService'


export default function NavgationBar(props) {
    
  return (
    <AppBar position='static'>
        <Toolbar>
            <Grid justifyContent="space-between" container>
                <Grid item>
                    <Typography variant='h6'>TODO LIST</Typography>
                </Grid>
                <Grid item>
                    <Button color="inherit" onClick={signout}>로그아웃</Button>
                </Grid>
            </Grid>
        </Toolbar>
    </AppBar>
  )
}
