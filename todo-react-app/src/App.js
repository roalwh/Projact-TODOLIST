
import './App.css';

// material-ui 컴포넌트 
import { Paper, List, Container } from '@mui/material';

// 컴포넌트 
import { useEffect, useState } from 'react';
import Todo from './Components/Todo';
import AddTodo from './Components/AddTodo';
import {call} from "./service/ApiService";

function App() {
  // 출력할 내용 설정
  const [items, setItems] = useState([]);


  // // 한번만 동작
  // useEffect(() => {
  //   // 통신설정
  // const requestOptions = {
  //   method: "GET",
  //   Headers: { "Content-Type": "application/json" },
  // }
  // fetch("http://localhost:8080/todo", requestOptions)
  //   .then((response) => response.json())
  //   .then(
  //     (response) => {
  //       setItems(response.data);
  //     },
  //     (error) => {

  //     }
  //   );
  // }, [])

  useEffect(() => {
    call("/todo", "GET", null)
      .then((response) => setItems(response.data));
  }, [])



  // 기능
  // 추가
  const AddItem = (item) => {
    // item.id = "ID-" + items.length;
    // item.done = false;
    // // 기존 배열 복사후 새 값 추가
    // setItems([...items, item]);
    call("/todo", "POST", item)
      .then((response) => setItems(response.data));
  };

  // 삭제
  const DeleteItem = (item) => {
    // // 삭제할 아이템을 찾는다
    // const newItems = items.filter(e => e.id !== item.id);
    // // 삭제할 아이템을 제외한 아이템을 다시 배열에 저장
    // setItems([...newItems]);
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
            return (
              <Todo item={item} key={item.id} editItem={editItem} deleteItem={DeleteItem} />
            )
          })
        }
      </List>
    </Paper>
  )

  return (
    <div className="App" >
      <Container maxWidth="md">
        <AddTodo Add={AddItem}></AddTodo>
        <div className="TodoList">{todoItems}</div>
      </Container>
    </div>
  );

}


export default App;
