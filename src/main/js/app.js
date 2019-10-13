'use strict';

import React, {Component} from 'react';
import { HeaderExampleSettingsIcon, ButtonExampleEmphasis  } from './module/app-ui'
import { MenuExampleSecondary } from './app-menu'

const ReactDOM = require('react-dom');
const when = require('when');
const client = require('./client');
const follow = require('./follow'); // function to hop multiple links by "rel"

class App extends Component {
        render()
        {
            return (

                <div>
                    <div>
                       <MenuExampleSecondary/>
                    </div>
                </div>
            );
        }

}

ReactDOM.render(
    <App />,
    document.getElementById('react')
);