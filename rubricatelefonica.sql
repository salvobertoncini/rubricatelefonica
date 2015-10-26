-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Creato il: Ott 25, 2015 alle 09:17
-- Versione del server: 5.6.26
-- Versione PHP: 5.5.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rubricatelefonica`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `addressbook`
--

CREATE TABLE IF NOT EXISTS `addressbook` (
  `id_addressbook` int(11) NOT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `addressbook`
--

INSERT INTO `addressbook` (`id_addressbook`, `id_user`) VALUES
(1, 4);

-- --------------------------------------------------------

--
-- Struttura della tabella `contact`
--

CREATE TABLE IF NOT EXISTS `contact` (
  `id_contact` int(11) NOT NULL,
  `id_people` int(11) NOT NULL,
  `id_addressbook` int(11) NOT NULL,
  `id_group` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `contact`
--

INSERT INTO `contact` (`id_contact`, `id_people`, `id_addressbook`, `id_group`) VALUES
(1, 6, 1, 1),
(2, 7, 1, 1),
(3, 8, 1, 1),
(4, 9, 1, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `group`
--

CREATE TABLE IF NOT EXISTS `group` (
  `id_group` int(11) NOT NULL,
  `name_group` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `group`
--

INSERT INTO `group` (`id_group`, `name_group`) VALUES
(1, 'default'),
(2, 'preferiti'),
(3, 'lavoro');

-- --------------------------------------------------------

--
-- Struttura della tabella `people`
--

CREATE TABLE IF NOT EXISTS `people` (
  `id_people` int(11) NOT NULL,
  `name_people` varchar(255) DEFAULT NULL,
  `surname_people` varchar(255) DEFAULT NULL,
  `telnumber1_people` varchar(255) DEFAULT NULL,
  `telnumber2_people` varchar(255) DEFAULT NULL,
  `email1_people` varchar(255) DEFAULT NULL,
  `email2_people` varchar(255) DEFAULT NULL,
  `note_people` text
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `people`
--

INSERT INTO `people` (`id_people`, `name_people`, `surname_people`, `telnumber1_people`, `telnumber2_people`, `email1_people`, `email2_people`, `note_people`) VALUES
(1, 'Salvo', 'Bertoncini', '3477129666', 'null', 'salvoberto@', 'null', 'null'),
(2, '', '', '', 'null', '', 'null', 'null'),
(3, 'alessio', 'provav', '2198309', 'null', '987', 'null', 'null'),
(4, 'asdasd', 'sfdasdasd', '34568345678', '214141', 'sdfghjksdfgh', '312451', '351221'),
(5, 'asdfghj', 'sdfghjk', 'zsdfxgchv', '124141', 'zsdfxgchvb', 'null', 'null'),
(7, 'Marco', 'Castano', 'il suo ', '123123', 'la sua', 'null', 'null'),
(8, 'Cristiana Ella', 'Princi', 'nascosto', '12414', 'la sua', 'sempre la sua va', 'ma null che cosa'),
(9, 'qrqwr', 'rqwtq', 'qwgqwg', 'qgqq', 'wewegwe', 'qtq', 'qtqrqtq');

-- --------------------------------------------------------

--
-- Struttura della tabella `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id_user` int(11) NOT NULL,
  `username_user` varchar(55) NOT NULL,
  `password_user` varchar(55) NOT NULL,
  `name_user` varchar(55) NOT NULL,
  `surname_user` varchar(55) NOT NULL,
  `telnumber_user` varchar(55) NOT NULL,
  `email_user` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `user`
--

INSERT INTO `user` (`id_user`, `username_user`, `password_user`, `name_user`, `surname_user`, `telnumber_user`, `email_user`) VALUES
(1, 'a', 'a', 'Salvo', 'Salvo', 'Salvo', 'Salvo'),
(2, 'q', 'q', 'Salvo', 'Salvo', 'Salvo', 'Salvo'),
(4, 'admin', 'admin', 'Salvo', 'Bertoncini', '2198706', 'saduhdisauyd');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `addressbook`
--
ALTER TABLE `addressbook`
  ADD PRIMARY KEY (`id_addressbook`);

--
-- Indici per le tabelle `contact`
--
ALTER TABLE `contact`
  ADD PRIMARY KEY (`id_contact`);

--
-- Indici per le tabelle `group`
--
ALTER TABLE `group`
  ADD PRIMARY KEY (`id_group`);

--
-- Indici per le tabelle `people`
--
ALTER TABLE `people`
  ADD PRIMARY KEY (`id_people`);

--
-- Indici per le tabelle `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `addressbook`
--
ALTER TABLE `addressbook`
  MODIFY `id_addressbook` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT per la tabella `contact`
--
ALTER TABLE `contact`
  MODIFY `id_contact` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT per la tabella `group`
--
ALTER TABLE `group`
  MODIFY `id_group` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT per la tabella `people`
--
ALTER TABLE `people`
  MODIFY `id_people` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT per la tabella `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
