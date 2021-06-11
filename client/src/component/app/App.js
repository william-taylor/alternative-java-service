
import 'antd/dist/antd.css';
import './App.css';

import React from 'react';
import {Layout, PageHeader} from 'antd';
const { Content } = Layout;

class App extends React.Component {
    render() {
        return (
            <Layout style={{ minHeight: '100vh' }}>
                <Layout>
                    <Content className={'content'}>
                        <PageHeader title="Fixed Rate Mortgage Calculator" />
                    </Content>
                </Layout>
            </Layout>
        );
    }
}

export default App;
