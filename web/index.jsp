<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 09.02.2020
  Time: 23:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>

<table width="100%" border="1">
    <tr>
      <td colspan="4">SETTING</td>
    </tr>

    <tr>
      <td>Select storage type</td>
      <td colspan="3">
        <select>
          <option value="ARRAY">ARRAY</option>
          <option value="COLLECTION">COLLECTION</option>
          <option selected value="DB">DB</option>
        </select>
      </td>
    </tr>

    <tr>
      <td>Select storage initor(only for array and collection)</td>
      <td colspan="3">
        <select>
          <option value="MEMORY">MEMORY</option>
          <option value="FILE">FILE</option>
          <option value="XML">XML</option>
          <option selected value="XMLSAX">XMLSAX</option>
        </select>
      </td>
    </tr>

    <tr>
      <td colspan="4">GET DATA</td>
    </tr>

    <tr>
      <td>Get all data</td>
      <td>
        <select>
          <option value="ALL">Get all data</option>
          <option value="CARGOS">Get all cargos</option>
          <option value="CARRIERS">Get all carriers</option>
          <option value="TRANSPORTATIONS">Get all transportations</option>
        </select>
      </td>
      <td colspan="2">
        <input type="submit" value="get data">

      </td>
    </tr>

    <tr>
      <td>Get data by Id</td>
      <td>
        <select>
          <option value="CARGO">Get cargo by ID</option>
          <option value="CARRIER">Get carrier by ID</option>
          <option value="TRANSPORTATION">Get transportation by ID</option>
        </select>
      </td>
      <td>
        <input type="text" name="ID" id="get-id" placeholder="ID">
      </td>
      <td>
        <input type="submit" value="get data">
      </td>
    </tr>

    <tr>
      <td>Get data by NAME</td>
      <td>
        <select>
          <option value="CARGO">Get cargo by name</option>
          <option value="CARRIER">Get carrier by name</option>
        </select>
      </td>
      <td>
        <input type="text" name="name" id="get-name" placeholder="name">
      </td>
      <td>
        <input type="submit" value="get data">
      </td>
    </tr>

    <tr>
      <td colspan="4">DELETE DATA</td>
    </tr>

    <tr>
      <td>Delete data by ID</td>
      <td>
        <select>
          <option value="CARGO">Delete cargo</option>
          <option value="CARRIER">Delete carrier</option>
          <option value="TRANSPORTATION">Delete transportation</option>
        </select>
      </td>
      <td>
        <input type="text" name="del-id" id="del-id" placeholder="ID">
      </td>
      <td>
        <input type="submit" value="get data">
      </td>
    </tr>

    <tr>
      <td colspan="4">GET SORTED CARGOS</td>
    </tr>

    <tr>
      <td>Select sort conditions</td>
      <td>
        <select>
          <option value="NAME">By name</option>
          <option value="WEIGHT">By weight</option>
          <option value="NAMEWEIGHT">By name and weihgt</option>
        </select>
      </td>
      <td>
        <select>
          <option value="ASC">In asc order</option>
          <option value="DESC">In desc order</option>
        </select>
      </td>
      <td>
        <input type="submit" value="get sorted data">

      </td>
    </tr>

    <tr>
      <td colspan="4">ADD DATA</td>
    </tr>

    <tr>
      <td>Choose add mode</td>
      <td colspan="4">
        <p><input name="add" type="radio" value="ONEELEM"> Add one element</p>
        <p><input name="add" type="radio" value="CARGOS"> Add cargos</p>
        <p><input name="add" type="radio" value="CARRIERS" checked> Add carriers</p>
        <p><input name="add" type="radio" value="CARGOSCARRIERS" checked> Add cargos and carriers</p>

      </td>

    </tr>

    <tr>
      <td colspan="4">
        <form action="" method="post" class="form">
          <div class="fieldset">
            <p>Add cargo</p>
            <input type="text" name="name" id="cargo-name" placeholder="Cargo name">
            <input type="text" name="weight" id="c-name" placeholder="Weight">

            <select name="c-type">
              <option value="OUTFIT">OUTFIT</option>
              <option value="PERISHABLE">PERISHABLE</option>
            </select>

            <input type="text" name="size" id="c-size" placeholder="Size">
            <select name="c-gender">
              <option value="MALE">MALE</option>
              <option value="FEMALE">FEMALE</option>
              <option value="UNISEX">UNISEX</option>

            </select>

            <input disabled type="text" name="temp" id="c-temp" placeholder="Store temperature">
            <input disabled type="text" name="date" id="c-date" placeholder="Expire date">


            <input type="submit" value="add">
            <input disabled type="number" name="cargo-counter" id="cargo-counter" placeholder="0">


          </div>
        </form>
      </td>
    </tr>

    <tr>
      <td colspan="4">
        <form action="" method="post" class="form">
          <div class="fieldset">
            <p>Add carrier</p>
            <input type="text" name="name" id="cr-name" placeholder="Carrier name">
            <input type="text" name="address" id="cr-address" placeholder="Address">

            <select name="cr-type">
              <option value="SHIP">SHIP</option>
              <option value="PLANE">PLANE</option>
              <option value="CAR">CAR</option>
              <option value="TRAIN">TRAIN</option>
            </select>

            <input type="submit" value="add">
            <input disabled type="number" name="carrier-counter" id="carrier-counter" placeholder="0">


          </div>
        </form>
      </td>
    </tr>

    <tr>
      <td colspan="4">
        <form action="" method="post" class="form">
          <div class="fieldset">
            <p>Add transportaton</p>
            <input type="text" name="trs-c-id" id="trs-c-id" placeholder="Cargo id">
            <input type="text" name="trs-cr-id" id="trs-cr-id" placeholder="Carrier id">

            <textarea type="text" name="description" id="descr" placeholder="Description"></textarea>

            <input type="text" name="bill-to" id="bill-to" placeholder="Bill to">
            <input type="text" name="trs-date" id="trs-date" placeholder="Date">


            <input type="submit" value="add">

          </div>
        </form>
      </td>
    </tr>

    <tr>
      <td colspan="4">UPDATE DATA</td>
    </tr>

    <tr>
      <td>Update data name by id</td>
      <td>
        <select>
          <option value="CARGO">Update cargo</option>
          <option value="CARRIER">Update carrier</option>
        </select>
      </td>
      <td>
        <input type="number" name="up-id" id="up-id" placeholder="ID">
        <input type="text" name="up-name" id="up-name" placeholder="new name">
      </td>
      <td>
        <input type="submit" value="update data">
      </td>
    </tr>

  </table>


</body>
</html>
