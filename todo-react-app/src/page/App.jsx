
import '../css/App.css';

// material-ui 컴포넌트 
import { Paper, List, Container } from '@mui/material';

// 컴포넌트 
import { useEffect, useState } from 'react';
import Todo from '../Components/Todo';
import AddTodo from '../Components/AddTodo';
import { call } from "../service/ApiService";
import NavgationBar from '../Components/NavgationBar';
import Loading from '../Components/Loading';

function App() {

  // 출력할 내용 설정
  const [items, setItems] = useState([]);
  // 로딩중
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if(localStorage.getItem("ACCESS_TOKEN")){
      call("/todo", "GET", null)
      .then((response) => setItems(response.data));
    setLoading(false);
    }else{
      window.location.href="/"
    }
  }, [])


  // 기능
  // 추가
  const AddItem = (item) => {
    call("/todo", "POST", item)
      .then((response) => setItems(response.data));
  };

  // 삭제
  const DeleteItem = (item) => {
    call("/todo", "DELETE", item)
      .then((response) => setItems(response.data));
  }

  // 수정
  const editItem = (item) => {
    // setItems([...items]);
    call("/todo", "PUT", item)
      .then((response) => setItems(response.data));
  }


  // 출력
  // items 길이만큼 출력
  let todoItems = items.length > 0 && (
    <Paper style={{ margin: '16' }} >
      <List>
        {
          //items 출력
          items.map((item) => {
            return (<Todo item={item} key={item.id} editItem={editItem} deleteItem={DeleteItem} />)
          })
        }
      </List>
    </Paper>
  )

  /* 로딩중이 아닐 때 렌더링할 부분 */
  let todoListPage = (
    <div>
      <NavgationBar />
      <Container maxWidth="md">
        <AddTodo addItem={AddItem} />
        <div className="TodoList">{todoItems}</div>
      </Container>
    </div>
  );

  /* 로딩중일 때 렌더링 할 부분 */
  let loadingPage = <Loading></Loading>;
  let content = loadingPage;
  if (!loading) {
    /* 로딩중이 아니면 todoListPage를 선택*/
    content = todoListPage;
  }
  /* 선택한 content 렌더링 */
  return <div className="App">{content}</div>;
}




export default App;
