import * as React from 'react';
import * as ThemeStyle from './css/style'
import Box from '@mui/material/Box';
import { Container } from '@mui/system';
import { Button, Divider, Grid, Typography } from '@mui/material';

export default function DisplayInfoBlock(props) {
    const button = true;
    
    const attributes = [...props.attributes];

    const typoStyle = {
        pb: 1,
        fontSize: '20px'
    }

    return (
            <Box
                component="form"
                sx={{
                '& .MuiTextField-root': { m: 1, width: '35ch' },
                border: 1,
                flexWrap: 'wrap',
                display: 'inline-flex',
                alignContent: 'center',
                pb: 1,
                mb: 5,
                justifyContent: 'center',
                width: 700,
                ...ThemeStyle.box
                }}
                noValidate
                autoComplete="off"
            >   
                <Container>
                    <div>
                        <Box
                            sx={{          
                                py:2,   
                                px: 0,               
                                display: 'flex',
                                flexDirection: 'row',
                                justifyContent: 'space-between',
                                lineHeight: 0
                            }}
                        >
                            <h3>{props.info.name}</h3>
                            {
                                button ?  
                                    <Button >Edit Details</Button> : <></>
                            }
                        </Box>
                    </div>
                    <Divider/>
                    <div>
                        <Box sx={{width: '100%', mt: 2}}>
                            <Grid container columnSpacing={20}>
                            {
                                attributes.map((e) => 
                                    <>            
                                    <Grid item xs={6} sx={{mb: 2}}>
                                        <Box
                                            sx={{
                                                display:'flex',
                                                alignItems: 'flex-start',
                                                flexDirection:'column',
                                                lineHeight: 0,
                                                pr: 10,
                                            }}>
                                            <item>
                                                <Typography sx={{...typoStyle, color: 'grey.500'}}>{e}</Typography>
                                            </item>
                                            {
                                                props.info[e] ? 
                                                <item>
                                                    <Typography sx={{...typoStyle}}>{props.info[e]}</Typography>
                                                </item> : 
                                                    <Typography sx={{...typoStyle}}>Null</Typography>
                                            }
                                        </Box>
                                    </Grid>
                                    </>
                                )
                            }
                            </Grid>
                        </Box>
                    </div>
                </Container>
            </Box>
    );
}
