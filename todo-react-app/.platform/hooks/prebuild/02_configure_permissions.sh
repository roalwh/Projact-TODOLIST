#!/bin/bash
SWAPFILE=/var/app/current/node_modules/.bin/react-scripts
if [ -f $SWAPFILE ]; then
 echo "$SWAPFILE found, skip"
 exit;
fi
/bin/chmod 766 $SWAPFILE

